export interface LoginDTO {
  userId: string;
  isAdmin: boolean;
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
  id: string;
  customerId: string;
  firstName: string;
  lastName: string;
  email: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface MeterDTO {
  id: string;
  type: 'water' | 'electricity' | 'gas';
  name: string;
  ownerId: string | null;
  lastReading: ReadingDTO;
  meterNumber: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface ReadingDTO {
  id: string;
  meterId: string;
  ownerId: string;
  value: string;
  trend: number;
  lastEditorName: string;
  lastEditReason: string;
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}

export interface IssueDTO {
  id: string;
  email: string;
  name: string;
  subject: string;
  message: string;
  status: 'UNRESOLVED' | 'RESOLVED';
  createdAt: string;
  updatedAt: string | null;
  deletedAt: string | null;
}
