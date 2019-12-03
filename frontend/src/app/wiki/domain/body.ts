export class Body {
  constructor(private _body: string) {

  }

  static empty() {
    return this.from("");
  }

  static from(body: string) {
    return new Body(body);
  }

  set body(value: string) {
    this._body = value;
  }

  get body(): string {
    return this._body;
  }
}
