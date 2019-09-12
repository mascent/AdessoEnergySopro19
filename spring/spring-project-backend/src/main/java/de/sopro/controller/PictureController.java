package de.sopro.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import de.sopro.data.MeterType;
import de.sopro.dto.PictureResponseDTO;
import de.sopro.response.classify.Classifications;
import de.sopro.response.detect.BoundingBox;
import de.sopro.response.detect.Predictions;
import de.sopro.response.parse.ParseResult;
import de.sopro.response.parse.Word;
import de.sopro.util.Pair;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

/**
 * The PictureController fetches requests containing pcitures and sends them to
 * the AzureWrapper.
 *
 */
@RestController
public class PictureController {

	public static final String CLASSIFY = "https://zaehlererkennung.azurewebsites.net/api/Classify";
	public static final String DETECTAREAS = "https://zaehlererkennung.azurewebsites.net/api/DetectAreas";
	public static final String PARSETEXT = "https://zaehlererkennung.azurewebsites.net/api/ParseText";
	public static final String TEAM = "HRS3105B";

	private OkHttpClient client = new OkHttpClient();

	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * @param pic The picture that should be analyzed as a base64 encoded String.
	 * @return The meter number and the reading of this meter. If one or both are
	 *         not found return error code.
	 * @throws IOException
	 */

	@PostMapping(path = "/api/picture")
	public PictureResponseDTO analyze(@RequestParam("file") MultipartFile file) throws IOException {

		File curr = null;

		if (!file.isEmpty()) {
			try {
				String uploadsDir = "/uploads";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}
				curr = new File(realPathtoUploads);
			} catch (Exception e) {
				System.out.println("Test");
			}

			file.transferTo(curr);
			BufferedImage bimb = ImageIO.read(curr);
			
			// check if all requirements are met
			if ((file.getSize() < 4000000) && azureRequirements(bimb)) {

				// Classification
				HttpUrl.Builder classifyUrlBuilder = HttpUrl.parse(CLASSIFY).newBuilder();
				String classifyUrl = classifyUrlBuilder.build().toString();

				RequestBody classifyRequestBody = new MultipartBody.Builder()
						.setType(MultipartBody.FORM).addFormDataPart("team", TEAM).addFormDataPart("file",
								file.getOriginalFilename(), RequestBody.create(curr, MediaType.parse("image/png")))
						.build();

				Request classifyRequest = new Request.Builder().post(classifyRequestBody).url(classifyUrl).build();

				Response classifyResponse = client.newCall(classifyRequest).execute();

				String classificationString = classifyResponse.body().string();

				Classifications classifications = new Gson().fromJson(classificationString, Classifications.class);

				// Detection
				HttpUrl.Builder detectionUrlBuilder = HttpUrl.parse(DETECTAREAS).newBuilder();
				String detectionUrl = detectionUrlBuilder.build().toString();

				RequestBody detectionRequestBody = new MultipartBody.Builder()
						.setType(MultipartBody.FORM).addFormDataPart("team", TEAM).addFormDataPart("file",
								file.getOriginalFilename(), RequestBody.create(curr, MediaType.parse("image/jpeg")))
						.build();

				Request detectionRequest = new Request.Builder().post(detectionRequestBody).url(detectionUrl).build();

				Response detectionResponse = client.newCall(detectionRequest).execute();

				String predictionString = detectionResponse.body().string();
				Predictions predictions = new Gson().fromJson(predictionString, Predictions.class);

				// Parse
				HttpUrl.Builder parseUrlBuilder = HttpUrl.parse(PARSETEXT).newBuilder();
				String parseUrl = parseUrlBuilder.build().toString();

				RequestBody parseRequestBody = new MultipartBody.Builder().addFormDataPart("file",
						file.getOriginalFilename(), RequestBody.create(curr, MediaType.parse("image/jpeg"))).build();

				Request parseRequest = new Request.Builder().post(parseRequestBody).url(parseUrl).build();

				Response parseResponse = client.newCall(parseRequest).execute();

				String parseString = parseResponse.body().string();
				ParseResult parseResult = new Gson().fromJson(parseString, ParseResult.class);

				// Logic
				MeterType type = classifications.lookupMeterType();

				Pair<BoundingBox, BoundingBox> boxes = predictions.findMaxProbabilityAreas();

				BoundingBox meterNumberArea = boxes.getFirst();
				BoundingBox meterValueArea = boxes.getSecond();

				// Since the API doesn't recognize either not the areas or not the parse result
				// we decided to use a dummy foto and only process the parse result.
				try {
					List<Word> meterValueWords = parseResult.findMatchingBox(meterValueArea,bimb);
					List<Word> meterNumberWords = parseResult.findMatchingBox(meterNumberArea,bimb);
					
					
					StringBuffer meterValueString = new StringBuffer();
					for (Word meterValueWord : meterValueWords) {
						meterValueString.append(meterValueWord.getText());
						
					}
					
					StringBuffer meterNumberString = new StringBuffer();
					for (Word meterNumberWord : meterNumberWords) {
						meterNumberString.append(meterNumberWord.getText());
					}
					Long metervalue = Long.parseLong(meterValueString.toString());
					
					return new PictureResponseDTO(type,metervalue,meterNumberString.toString());					
				} catch (Exception e) {
					e.printStackTrace();
					throw new IllegalArgumentException("Azure failed to retrieve Data");
				}
	
			}

		}
		throw new IllegalArgumentException("Something went wrong");
	

	}


	private boolean azureRequirements(BufferedImage bimb) {
		return  bimb.getWidth() >= 50 
				&& bimb.getWidth() <= 4200
				&& bimb.getHeight() >= 50 
				&& bimb.getHeight() <= 4200 
				&& bimb.getHeight() * bimb.getWidth() <= 10000000;
	}
}
