import ApiConfig from './config';
import MetersComponent from './meters-component';
import UsersComponent from './users-component';

const config = new ApiConfig();
const users = new UsersComponent(config);
const meters = new MetersComponent(config);

export { config, users, meters };
