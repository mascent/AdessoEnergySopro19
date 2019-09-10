import React from 'react';
import AdminAppBar from '../components/appbar/admin-app-bar';
import ContainerCard from '../components/generics/container-card';
import styles from './admin-dashboard.module.scss';
import Illustrations from '../components/generics/illustrations';
import Input from '../components/generics/input';
import { SubTitle } from '../components/generics/text';

const AdminDashboard: React.FC = () => {
  return (
    <div>
      <AdminAppBar />
      <ContainerCard className={styles.container}>
        <ContainerCard className={styles.leftContainer}>
          <SubTitle className={styles.title}>Kunden</SubTitle>
          <Input
            id="SearchBar"
            type="text"
            label=""
            className={styles.searchBar}
            placeholder="Nach einem Kunden suchen"
          />
        </ContainerCard>

        <div className={styles.bl}>
          <Illustrations className={styles.illustrations} type="NoSelected" />
        </div>
      </ContainerCard>
    </div>
  );
};

export default AdminDashboard;
