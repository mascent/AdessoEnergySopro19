import React from 'react';
import styles from './select-call.module.scss';
import Illustrations from '../generics/illustrations';
import { SectionHeader } from '../generics/text';
import { RouteComponentProps } from '@reach/router';

interface SelectCallToActionProps {
  text: string;
}

const SelectCallToAction: React.FC<SelectCallToActionProps> = ({ text }) => (
  <div className={styles.container}>
    <Illustrations type="NoSelected" />
    <SectionHeader>{text}</SectionHeader>
  </div>
);

export const SelectMeter: React.FC<RouteComponentProps> = () => (
  <SelectCallToAction text="Wähle einen Zähler aus um mehr Informationen anzuzeigen" />
);
