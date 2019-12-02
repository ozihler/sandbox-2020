export class Title {

  private constructor(private _title: string) {
  }

  get title(): string {
    return this._title;
  }

  static empty() {
    return this.from("");
  }

  static from(title: string): Title {
    return new Title(title);
  }
}
