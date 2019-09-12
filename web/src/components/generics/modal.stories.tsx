import React from 'react';
import { storiesOf } from '@storybook/react';
import Modal, { ModalBody, ModalFooter } from './modal';
import { Paragraph } from './text';
import { SecondaryButton } from './button';
import { action } from '@storybook/addon-actions';

const View: React.FC = () => {
  const [isOpen, setOpen] = React.useState(true);
  const closeButton = React.useRef<HTMLButtonElement>(null);

  return (
    <>
      <SecondaryButton onClick={() => setOpen(true)}>
        Show modal
      </SecondaryButton>
      <Modal
        title="Test Modal"
        isOpen={isOpen}
        defaultFocusRef={closeButton}
        onModalCloseRequest={() => setOpen(false)}
      >
        <ModalBody>
          <Paragraph>
            This is a test modal. The size of the modal depends on the size of
            the content. The default Focus should be given to the least
            constructive element.
          </Paragraph>
        </ModalBody>
        <ModalFooter>
          <SecondaryButton ref={closeButton} onClick={() => setOpen(false)}>
            Close
          </SecondaryButton>
          <SecondaryButton onClick={action('Confirming')}>
            Confirm
          </SecondaryButton>
        </ModalFooter>
      </Modal>
    </>
  );
};

storiesOf('Generics |Modal', module).add('default', () => <View />);
