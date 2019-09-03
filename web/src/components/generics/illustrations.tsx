import React from 'react';
import { ReactComponent as Logo } from './assets/auth.svg';
import { ReactComponent as Blanket } from './assets/paper.svg';

export type IllustrationType = 'Auth' | 'NoSelected';

interface IllustrationProps {
  className?: string;
  type: IllustrationType;
}
const Illustrations: React.FC<IllustrationProps> = ({ type, className }) => {
  switch (type) {
    case 'Auth':
      return <Logo data-testid="Auth" className={className} />;
    case 'NoSelected':
      return <Blanket data-testid="NoSelected" className={className} />;
    default:
      throw new TypeError('Unknown Illustrations type.');
  }
};

export default Illustrations;
