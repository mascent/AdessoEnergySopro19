# Adesso Energy Web App

> This project was bootstrapped with
> [Create React App](https://github.com/facebook/create-react-app). Furthermore
> we are using [Storybook](https://storybook.js.org) as an isolated component
> development environment.

## Available Scripts

| Command                   | Description                                                                                                        |
| ------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| `npm start`               | Runs the app in development mode.                                                                                  |
| `npm run build`           | Builds the app for production to the `build` folder. It optimizes the build for the best performance.              |
| `npm run storybook`       | Runs the storybook in development mode.                                                                            |
| `npm run build-storybook` | Builds the storybook for a productions deployment.                                                                 |
| `npm test`                | Launches the jest runner in an interactive watch mode. Setting `CI` to true will run all test and ends afterwards. |
| `npm test -- --coverage`  | Creates a coverage report.                                                                                         |

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
