import {Body} from "../../../domain/body";

export class CreateWikiPagesFromBodyRequest {
  body: string;

  constructor(body: Body) {
    this.body = body.body;
  }

}
