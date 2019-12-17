export class Body {
  constructor(private _body: string) {

  }

  get body(): string {
    return this._body;
  }

  set body(value: string) {
    this._body = value;
  }

  static empty() {
    return this.from('');
  }

  static from(body: string) {
    return new Body(body);
  }
}
