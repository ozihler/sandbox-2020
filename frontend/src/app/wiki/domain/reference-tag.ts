export class ReferenceTag {
  constructor(private _referenceTag: string) {
  }

  get referenceTag(): string {
    return this._referenceTag;
  }

  static empty() {
    return this.from("");
  }

  static from(referenceTag: string) {
    return new ReferenceTag(referenceTag);
  }

  public isEmpty(): boolean {
    return !this._referenceTag;
  }
}
