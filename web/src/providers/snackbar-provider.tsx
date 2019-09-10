import React from 'react';
import uniqId from 'uniqid';
import Logger from '../services/logger/logger';
import SnackBarsManager from '../components/snackbar/snackbar-manager';

type SnackBarType = 'success' | 'info' | 'warning' | 'error';

export interface SnackBar {
  id: string;
  type: SnackBarType;
  text: string;
  action?: {
    text: string;
    handler: () => void;
  };
}

const SnackBarContext = React.createContext<
  ((snackBar: SnackBar) => void) | undefined
>(undefined);

const SnackBarProvider: React.FC = ({ children }) => {
  const [snackBars, setSnackBars] = React.useState<SnackBar[]>([]);

  function addSnackBar(snackBar: SnackBar) {
    // Don't show a snackbar with the same content already visible
    if (
      snackBars.find(
        sb => sb.text === snackBar.text && sb.type === snackBar.type
      )
    )
      return;

    Logger.logBreadcrumb(
      'info',
      'snackbar-provider',
      `Show SnackBar with id: ${snackBar.id}.`
    );
    setSnackBars(list => [...list, snackBar]);
    setTimeout(() => hideSnackBar(snackBar.id), 5000);
  }

  function hideSnackBar(id: string) {
    Logger.logBreadcrumb(
      'info',
      'snackbar-provider',
      `Hide SnackBar with id: ${id}.`
    );
    setSnackBars(list => list.filter(snackBar => snackBar.id !== id));
  }

  return (
    <>
      <SnackBarContext.Provider value={addSnackBar}>
        {children}
      </SnackBarContext.Provider>
      <SnackBarsManager snackBars={snackBars} hide={hideSnackBar} />
    </>
  );
};

function useSnackBar() {
  const addSnackBar = React.useContext(SnackBarContext);

  function createSnackBar(
    type: SnackBarType,
    text: string,
    action?: {
      text: string;
      handler: () => void;
    }
  ) {
    if (!addSnackBar)
      throw new Error('useSnackBar must be used within a SnackBarProvider.');

    addSnackBar({ id: uniqId(), type, text, action });
  }

  return createSnackBar;
}

export { SnackBarProvider, useSnackBar };
