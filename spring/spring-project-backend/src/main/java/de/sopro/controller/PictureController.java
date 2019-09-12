package de.sopro.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import de.sopro.data.MeterType;
import de.sopro.response.classify.Classification;
import de.sopro.response.classify.Classifications;
import de.sopro.response.detect.BoundingBox;
import de.sopro.response.detect.Predictions;
import de.sopro.response.parse.ParseResult;
import de.sopro.util.Pair;
import de.sopro.util.exception.UnreadableFotoException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * The PictureController fetches requests containing pcitures and sends them 
 * to the AzureWrapper.
 *
 */
@RestController
public class PictureController {
	
	public static final String CLASSIFY = "https://zaehlererkennung.azurewebsites.net/api/Classify";
	public static final String DETECTAREAS = "https://zaehlererkennung.azurewebsites.net/api/DetectAreas";
	public static final String PARSETEXT = "https://zaehlererkennung.azurewebsites.net/api/ParseText";
	public static final String TEAM = "HRS3105B";
	
	private OkHttpClient client = new OkHttpClient();

	/**
	 * 
	 * @param pic   The picture that should be analyzed as a base64 encoded String.
	 * @return The meter number and the reading of this meter. If one or both are
	 *         not found return error code.
	 * @throws IOException 
	 */
	@PostMapping(path = "/api/pictures", params = {"pic"})
	@CrossOrigin
	public String analyze(@RequestParam File file) throws IOException {
	
		String path;
		
		//Classification
		HttpUrl.Builder classifyUrlBuilder = HttpUrl.parse(CLASSIFY).newBuilder();
		String classifyUrl = classifyUrlBuilder.build().toString();
		
		RequestBody classifyRequestBody = new MultipartBody.Builder()
											  .setType(MultipartBody.FORM)
											  .addFormDataPart("team",TEAM)
											  .addFormDataPart("file",file.getName(), 
													  RequestBody.create(file,MediaType.parse("image/png")))
											  .build();
				
		Request classifyRequest = new Request.Builder()
									  .post(classifyRequestBody)
									  .url(classifyUrl)
									  .build();
							  
		Response classifyResponse = client.newCall(classifyRequest).execute();
		
		Classifications classifications = new Gson().fromJson(classifyResponse.body().string(), Classifications.class);
	
		// Detection
		HttpUrl.Builder detectionUrlBuilder = HttpUrl.parse(DETECTAREAS).newBuilder();
		String detectionUrl = classifyUrlBuilder.build().toString();
		
		RequestBody detectionRequestBody = new MultipartBody.Builder()
											  //.setType(MultipartBody.FORM)
											  .addFormDataPart("team",TEAM)
											  .addFormDataPart("file",file.getName(), 
													  RequestBody.create(file,MediaType.parse("image/png")))
											  .build();
				
		Request detectionRequest = new Request.Builder()
									  .post(detectionRequestBody)
									  .url(detectionUrl)
									  .build();
							  
		Response detectionResponse = client.newCall(classifyRequest).execute();
		
		Predictions predictions = new Gson().fromJson(detectionResponse.body().string(), Predictions.class);
		
		
		// Parse	
		HttpUrl.Builder parseUrlBuilder = HttpUrl.parse(DETECTAREAS).newBuilder();
		String parseUrl = classifyUrlBuilder.build().toString();
		
		RequestBody parseRequestBody = new MultipartBody.Builder()
											  .addFormDataPart("file",file.getName(), 
													  RequestBody.create(file,MediaType.parse("image/png")))
											  .build();
				
		Request parseRequest = new Request.Builder()
									  .post(parseRequestBody)
									  .url(parseUrl)
									  .build();
							  
		Response parseResponse = client.newCall(classifyRequest).execute();
		
		ParseResult parseResult = new Gson().fromJson(parseResponse.body().string(), ParseResult.class);
		
		// Logic
		MeterType type = classifications.lookupMeterType();
		
		Pair<BoundingBox,BoundingBox> boxes = predictions.findMaxProbabilityAreas();
		
		BoundingBox meterNumberArea = boxes.getFirst();
		BoundingBox meterValueArea = boxes.getSecond();
		
		try {
			String meterResult = parseResult.findMatchingBox(meterNumberArea);
		} catch (UnreadableFotoException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
		
  
}


