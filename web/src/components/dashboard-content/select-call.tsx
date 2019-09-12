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
  <section className={styles.center}>
    <SelectCallToAction text="Wähle einen Zähler aus um mehr Informationen anzuzeigen" />
  </section>
);

export const SelectUser: React.FC<RouteComponentProps> = () => (
  <section className={styles.center}>
    <SelectCallToAction text="Wähle einen Kunden aus um mehr Informationen anzuzeigen" />
  </section>
);

export const SelectIssue: React.FC<RouteComponentProps> = () => (
  <section className={styles.center}>
    <SelectCallToAction text="Wähle ein Ticket aus um mehr Informationen anzuzeigen" />
  </section>
);
