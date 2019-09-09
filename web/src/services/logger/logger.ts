type LogLevel = 'debug' | 'info' | 'warning' | 'error';

class Logger {
  /**
   * Logs a breadcrumb to the console.
   * @param level Log level.
   * @param category Useful to categorize the breadcrumbs
   * @param message Message to add to the breadcrumb
   */
  public static logBreadcrumb(
    level: LogLevel,
    category: string,
    message: string
  ) {
    if (process.env.NODE_ENV === 'development') {
      // Add console output
      console.groupCollapsed(
        '%cLogger: Breadcrumb - %s : %s',
        'color: SeaGreen',
        category,
        message
      );
      console.log('Level: %c%s', 'font-weight: bold', level);
      console.log('Category: %c%s', 'font-weight: bold', category);
      console.log('Message: %c%s', 'font-weight: bold', message);
      console.groupEnd();
    }
  }

  /**
   * Logs an error to the console if the environment is set to development.
   * @param exp Exception
   */
  public static captureException(exp: Error) {
    if (process.env.NODE_ENV === 'development') {
      // Add console output
      console.groupCollapsed(
        '%cLogger: Exception - %s',
        'color: Crimson',
        exp.message
      );
      console.error(exp);
      console.groupEnd();
    }
  }
}

export default Logger;
