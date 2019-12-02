export class Body {
  constructor(private body: string) {

  }

  static empty() {
    return this.from("");
  }

  static from(body: string) {
    return new Body(body);
  }
}
