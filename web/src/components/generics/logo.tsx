import React from 'react';
import { ReactComponent as LogoNoText } from './assets/logo-drop.svg';
import { ReactComponent as LogoWithBg } from './assets/logo.svg';
import { ReactComponent as LogoTextHorizontal } from './assets/logo-text-hor.svg';
import { ReactComponent as LogoTextHorizontalStacked } from './assets/logo-text-stacked-hor.svg';
import { ReactComponent as LogoTextVertical } from './assets/logo-text-ver.svg';

export type LogoType =
  | 'with-bg'
  | 'no-text'
  | 'text-vertical'
  | 'text-horizontal'
  | 'text-horizontal-stacked';

interface LogoProps {
  className?: string;
  type: LogoType;
}

const Logo: React.FC<LogoProps> = ({ type, className }) => {
  switch (type) {
    case 'with-bg':
      return <LogoWithBg data-testid="with-bg" className={className} />;
    case 'no-text':
      return <LogoNoText data-testid="no-text" className={className} />;
    case 'text-vertical':
      return (
        <LogoTextVertical data-testid="text-vertical" className={className} />
      );
    case 'text-horizontal':
      return (
        <LogoTextHorizontal
          data-testid="text-horizontal"
          className={className}
        />
      );
    case 'text-horizontal-stacked':
      return (
        <LogoTextHorizontalStacked
          data-testid="text-horizontal-stacked"
          className={className}
        />
      );
    default:
      throw new TypeError('Unknown logo type provided.');
  }
};

export default Logo;
