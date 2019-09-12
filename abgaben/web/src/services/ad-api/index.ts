import ApiConfig from './config';
import MetersComponent from './meters-component';
import UsersComponent from './users-component';
import IssueComponent from './issues-component';
import ReadingComponent from './readings-component';
import Authentication from './authentication-component';

const config = new ApiConfig();
const auth = new Authentication(config);
const users = new UsersComponent(config);
const meters = new MetersComponent(config);
const issues = new IssueComponent(config);
const readings = new ReadingComponent(config);

export { auth, issues, readings, config, users, meters };
