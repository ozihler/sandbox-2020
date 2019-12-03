import {TestBed} from '@angular/core/testing';

import {CreateWikiPageFromTitleUseCase} from './create-wiki-page.service';

describe('CreateWikiPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateWikiPageFromTitleUseCase = TestBed.get(CreateWikiPageFromTitleUseCase);
    expect(service).toBeTruthy();
  });
});
