import { MeterType } from './provider-data-interfaces';

export interface LoginDTO {
  id: number;
  role: 'User' | 'Admin';
  user: string;
}

export interface Paging<T> {
  content: T[];
  size: number;
  page: number;
  totalPages: number;
  total: number;
  first: boolean;
  last: boolean;
}

export interface UserDTO {
  id: number;
  customerNumber: string;
  firstName: string;
  lastName: string;
  email: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface MeterDTO {
  id: number;
  type: 'Water' | 'Electricity' | 'Gas';
  name: string;
  ownerId: number | null;
  lastReading: ReadingDTO;
  meterNumber: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface ReadingDTO {
  id: number;
  meterId: number;
  ownerId: number;
  value: string;
  lastEditorName: string;
  lastEditReason: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface IssueDTO {
  id: number;
  email: string;
  name: string;
  subject: string;
  message: string;
  isClosed: boolean;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface NewUser {
  customerNumber: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

export interface NewMeter {
  initialValue: string;
  ownerId: string;
  meterNumber: string;
  name: string;
  type: MeterType;
}
