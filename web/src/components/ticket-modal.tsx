import React from 'react';
import { Title } from './generics/text';
import { PrimaryButton, SecondaryButton } from './generics/button';
import Input from './generics/input';
import Modal, { ModalBody, ModalFooter } from './generics/modal';
import { useInputValidation } from 'use-input-validation';
import styles from './ticket-modal.module.scss';
import Textarea from './generics/textarea';
import { isValidEmail } from '../lib/validators';

interface TicketModalProps {
  onSend: (
    name: string,
    email: string,
    subject: string,
    message: string
  ) => void;
  isOpen: boolean;
  closeModal: () => void;
}

function isEmpty(text: string) {
  return text !== '';
}

const TicketModal: React.FC<TicketModalProps> = ({
  closeModal,
  isOpen,
  onSend
}) => {
  const ref = React.useRef(null);

  const name = useInputValidation<string, string>(
    '',
    'Kein valider Name',
    isEmpty
  );

  const email = useInputValidation<string, string>(
    '',
    'Keine valide Email-Adresse',
    isValidEmail
  );

  const subject = useInputValidation<string, string>(
    '',
    'Kein valider Betreff',
    isEmpty
  );

  const message = useInputValidation<string, string>(
    '',
    'Keine valide Nachricht',
    isEmpty
  );

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const nameIdValid = name.validate();
    const emailValid = email.validate();
    const subjectIDValid = subject.validate();
    const emailIDValid = email.validate();
    const messageIDValid = message.validate();

    if (
      !(
        nameIdValid &&
        emailValid &&
        subjectIDValid &&
        emailIDValid &&
        messageIDValid
      )
    )
      return;

    onSend(name.value, email.value, subject.value, message.value);
  }

  return (
    <Modal
      isOpen={isOpen}
      title="Support Ticket erstellen"
      defaultFocusRef={ref}
      onModalCloseRequest={closeModal}
    >
      <form onSubmit={handleSubmit}>
        <ModalBody>
          <Input
            id="nameID"
            type="text"
            label="Name"
            value={name.value}
            onChange={value => name.setValue(value)}
            onBlur={name.validate}
            error={name.error}
          />

          <Input
            id="Email"
            label="Email"
            type="text"
            value={email.value}
            onChange={value => email.setValue(value)}
            onBlur={email.validate}
            error={email.error}
          />

          <Input
            id="Subject"
            label="Betreff"
            type="text"
            value={subject.value}
            onChange={value => subject.setValue(value)}
            onBlur={subject.validate}
            error={subject.error}
          />

          <Textarea
            id="Message"
            label="Nachricht"
            value={message.value}
            onChange={value => message.setValue(value)}
            onBlur={message.validate}
            error={message.error}
            className={styles.message}
          />
        </ModalBody>
        <ModalFooter>
          <SecondaryButton
            ref={ref}
            onClick={closeModal}
            className={styles.button}
          >
            Abbrechen
          </SecondaryButton>

          <PrimaryButton onClick={() => {}}>Senden</PrimaryButton>
        </ModalFooter>
      </form>
    </Modal>
  );
};

export default TicketModal;
