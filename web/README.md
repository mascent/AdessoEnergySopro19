# Adesso Energy Web App

> This project was bootstrapped with
> [Create React App](https://github.com/facebook/create-react-app). Furthermore
> we are using [Storybook](https://storybook.js.org) as an isolated component
> development environment.

## Available Scripts

| Command                        | Description                                                                                                        |
| ------------------------------ | ------------------------------------------------------------------------------------------------------------------ |
| `npm start`                    | Runs the app in development mode.                                                                                  |
| `npm run build`                | Builds the app for production to the `build` folder. It optimizes the build for the best performance.              |
| `npm run storybook`            | Runs the storybook in development mode.                                                                            |
| `npm run build-storybook`      | Creates a test run result and builds the storybook for a productions deployment.                                   |
| `npm test`                     | Launches the jest runner in an interactive watch mode. Setting `CI` to true will run all test and ends afterwards. |
| `npm test -- --coverage`       | Creates a coverage report.                                                                                         |
| `npm run test:generate-output` | Generates a test run result which can be used in storybook to display tests                                        |

## How to deploy the web application

The web app can easily be deployed as a docker image. At build time 2
environment variables are required to configure the react build.

| Variable          | Description                                              |
| ----------------- | -------------------------------------------------------- |
| `base_domain`     | The domain the app is deployed to. Default: _nothing_    |
| `api_base_domain` | Base domain of the api. Default: `https://adesso.energy` |

### Steps to deploy the docker image

1. Build the image

```bash
docker build --build-arg base_domain=<base-domain> --build-arg api_base_domain=<api-domain> -t web-app .
```

> If the website will be accessed via a non default port (80, 443), the port
> must be specified in the argument.
>
>The base_domain is only required if the
> website is **not** deployed at the server root.

2. Run the image

```bash
docker run -p <port>:80 --rm web-app
```

## Conventions

### Naming

- **Files**: Are written in lowercase with dashes between the words. Exp:
  `user-interface.tsx`
- **Variables/Const**: Are named in camelCase. Exp: `userName`
- **Components/Types/Interface**: Are named in upper CamelCase. Exp:
  `PrimaryButton`

## App Routes

| Route                           | Description                              |
| ------------------------------- | ---------------------------------------- |
| /login                          | Login Page                               |
| / => Redirect to /counters      | User \| Home, Weiterleitung zu /counters |
| /counters                       | User \| Alle Zähler                      |
| /counters/<id>                  | User \| Zähler Info                      |
| /admin/users                    | Admin \| Alle User                       |
| /admin/users/<id>               | Admin \| User Info + alle seine Zähler   |
| /admin/users/<id>/counters/<id> | Admin \| Zähler Info für einen User      |
| /admin/tickets                  | Admin \| Alle Tickets                    |
| /admin/tickets/<id>             | Admin \| Ticket Info                     |
