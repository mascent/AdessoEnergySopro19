class ApiConfig {
  private isInitiated: boolean = false;
  private _baseUrl: string | undefined;
  private _token: string | undefined;

  public init(options: { baseUrl: string }) {
    if (this.isInitiated)
      throw new Error('Adesso Energy API can only be initiated once.');

    this.isInitiated = true;
    this._baseUrl = options.baseUrl;
  }

  public get baseUrl(): string {
    if (!this.isInitiated || !this._baseUrl)
      throw new Error('Adesso Energy API need to be initiated first.');

    return this._baseUrl;
  }

  public get token(): string {
    if (!this._token)
      throw new Error('Token is not set. Make sure to set a token first.');

    return this._token;
  }

  public setToken(username: string, password: string) {
    if (username.trim() === '' || password.trim() === '')
      throw new TypeError(
        'Invalid token parameter. Token can not be an empty string'
      );

    this._token = btoa(`${username}:${password}`);
  }

  public resetToken() {
    this._token = undefined;
  }
}

export default ApiConfig;
