import React from 'react';
import { RouteComponentProps, Router } from '@reach/router';

const Wrapper: React.FC<RouteComponentProps & { content: React.ReactNode }> = ({
  content
}) => <>{content}</>;

export const WithRouter: React.FC = ({ children }) => {
  return (
    <Router>
      <Wrapper default content={children}></Wrapper>
    </Router>
  );
};
