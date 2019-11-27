package com.zihler.wiki.domain.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReferenceTagsTest {

    @Test
    void test_match_reference_tags_in_body__returns_all_valid_reference_tags() {
        assertTrue(Body.from("#").foundReferenceTags().isEmpty());
        assertTrue(Body.from("#Hello").foundReferenceTags().containsAll(ReferenceTag.from("#Hello")));

        assertTrue(Body.from("#HelloWorld").foundReferenceTags().containsAll(ReferenceTag.from("#HelloWorld")));

        ReferenceTags sameReferenceTagMultipleTimes = Body.from("#HelloWorld;#HelloWorld.#HelloWorld..;#HelloWorld:;.  #HelloWorld").foundReferenceTags();
        assertEquals(1, sameReferenceTagMultipleTimes.count());
        assertTrue(sameReferenceTagMultipleTimes.containsAll(ReferenceTag.from("#HelloWorld")));

        ReferenceTags twoReferenceTags = Body.from("#HelloWorld #Hello").foundReferenceTags();
        assertEquals(2, twoReferenceTags.count());
        assertTrue(twoReferenceTags.containsAll(ReferenceTag.from("#Hello")));
        assertTrue(twoReferenceTags.containsAll(ReferenceTag.from("#HelloWorld")));

        ReferenceTags threeReferenceTags = Body.from("#HelloWorld. hello World ##HelloWorldWorld ##this is a larger text #Hello will be the new Word For##Me beCause# I#Try outA #LoT").foundReferenceTags();
        assertEquals(3, threeReferenceTags.count());
        assertTrue(threeReferenceTags.containsAll(ReferenceTag.from("#Hello"), ReferenceTag.from("#HelloWorld"), ReferenceTag.from("#LoT")));
    }
}
