import React from 'react';
import ContainerCard from './generics/container-card';
import UserAppBar from './appbar/user-app-bar';
import styles from './user-interface.module.scss';
import Illustrations from './generics/illustrations';
import Input from './generics/input';
import { SubTitle } from './generics/text';
import Magnify from 'mdi-react/MagnifyIcon';

const UserInterface: React.FC = () => {
  return (
    <div>
      <UserAppBar />
      <ContainerCard className={styles.container}>
        <div className={styles.leftContainer}>
          <SubTitle>Zähler</SubTitle>
          <Magnify />
          <Input
            id="SearchBar"
            type="text"
            label=""
            className={styles.searchBar}
            placeholder="Nach einem Zähler suchen"
          />
        </div>
        <div className={styles.bl}>
          <Illustrations className={styles.illustrations} type="NoSelected" />
        </div>
      </ContainerCard>
    </div>
  );
};

export default UserInterface;
