import React from 'react';
import ReactModal from 'react-modal';

import styles from './modal.module.scss';
import './modal.scss';

import { SubTitle } from './text';

interface ModalProps {
  title: string;
  defaultFocusRef: React.RefObject<HTMLButtonElement>;

  isOpen: boolean;
  onModalCloseRequest: () => void;
}

const Modal: React.FC<ModalProps> = ({
  title,
  isOpen,
  defaultFocusRef,
  onModalCloseRequest,
  children
}) => {
  const appEl = React.useRef(document.querySelector('#root'));

  if (!appEl.current) return null;

  function focusRef() {
    if (!defaultFocusRef.current) return;
    defaultFocusRef.current.focus();
  }

  return (
    <ReactModal
      isOpen={isOpen}
      onRequestClose={onModalCloseRequest}
      onAfterOpen={focusRef}
      shouldFocusAfterRender={false}
      contentLabel={title}
      appElement={appEl.current}
      className="react-modal-content"
      overlayClassName="react-modal-overlay"
      closeTimeoutMS={200}
    >
      <SubTitle className={styles.modalHeader}>{title}</SubTitle>
      {children}
    </ReactModal>
  );
};

export default Modal;

export const ModalBody: React.FC = ({ children }) => (
  <div className={styles.modalBody}>{children}</div>
);

export const ModalFooter: React.FC = ({ children }) => (
  <div className={styles.modalFooter}>{children}</div>
);
