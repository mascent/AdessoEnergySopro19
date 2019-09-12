import { useEffect } from 'react';

/**
 * Sets the title of the document. This can be used in screens to change what
 * is shown on a browser tab.
 * @param title Screen title
 */
function useDocumentTitle(title: string) {
  useEffect(() => {
    if (title.trim() === '') {
      document.title = 'Adesso Energy';
    } else {
      document.title = `${title.trim()} - Adesso Energy`;
    }
  }, [title]);
}

export default useDocumentTitle;
