import React from 'react';
import cx from 'classnames';
import styles from './illustrations.module.scss';
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
      return (
        <Logo
          data-testid="Auth"
          className={cx(styles.illustration, className)}
        />
      );
    case 'NoSelected':
      return (
        <Blanket
          data-testid="NoSelected"
          className={cx(styles.illustration, className)}
        />
      );
    default:
      throw new TypeError('Unknown Illustrations type.');
  }
};

export default Illustrations;
