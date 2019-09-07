export function getApiBaseDomain(): string {
  const domain = process.env.REACT_APP_API_BASE_DOMAIN;

  if (typeof domain !== 'string')
    throw new Error(
      'No API Base Domain was provided. Please provide REACT_APP_API_BASE_DOMAIN as an env variable during deployment.'
    );

  return domain;
}
