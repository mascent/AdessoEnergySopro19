import ApiConfig from './config';
import MetersComponent from './meters-component';
import UsersComponent from './users-component';
import IssueComponent from './issues-component';
import ReadingComponent from './readings-component';

const config = new ApiConfig();
const users = new UsersComponent(config);
const meters = new MetersComponent(config);
const issues = new IssueComponent(config);
const readings = new ReadingComponent(config);

export { issues, readings, config, users, meters };
