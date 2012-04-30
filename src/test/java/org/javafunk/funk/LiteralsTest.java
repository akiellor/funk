/*
 * Copyright (C) 2011 Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.javafunk.funk.builders.ListBuilder;
import org.javafunk.funk.datastructures.tuples.*;
import org.javafunk.funk.testclasses.Age;
import org.javafunk.funk.testclasses.Colour;
import org.javafunk.funk.testclasses.Location;
import org.javafunk.funk.testclasses.Name;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.javafunk.funk.Literals.*;
import static org.javafunk.funk.testclasses.Age.age;
import static org.javafunk.funk.testclasses.Colour.colour;
import static org.javafunk.funk.testclasses.Location.location;
import static org.javafunk.funk.testclasses.Name.name;
import static org.junit.Assert.assertThat;

public class LiteralsTest {
    @Test
    public void shouldReturnACollectionContainingTheSuppliedElements() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(5, 10, 15);

        // When
        Collection<Integer> actualList = collectionWith(5, 10, 15);

        // Then
        assertThat(actualList, is(expectedCollection));
    }

    @Test
    public void shouldAllowMoreElementsToBeAddedToTheCollectionWithAnd() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(5, 10, 15, 20, 25, 30);

        // When
        Collection<Integer> actualCollection = collectionWith(5, 10, 15).and(20, 25, 30);

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheCollectionWithAnd() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(5, 10, 15, 20, 25, 30);
        Integer[] elementArray = new Integer[]{20, 25, 30};

        // When
        Collection<Integer> actualList = collectionWith(5, 10, 15).and(elementArray);

        // Then
        assertThat(actualList, is(expectedCollection));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheCollectionWithAnd() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> someOtherElements = asList(4, 5, 6);

        // When
        Collection<Integer> actualCollection = collectionWith(1, 2, 3).and(someOtherElements);

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldAllowElementsToBeAddedToACollectionWithWith() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(1, 2, 3, 4, 5, 6);

        // When
        Collection<Integer> actualCollection = collectionOf(Integer.class)
                .with(1, 2, 3)
                .with(4, 5, 6)
                .build();

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheCollectionWithWith() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(5, 10, 15, 20, 25, 30);
        Integer[] elementArray = new Integer[]{20, 25, 30};

        // When
        Collection<Integer> actualCollection = collectionWith(5, 10, 15).with(elementArray);

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheCollectionWithWith() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> firstInputIterable = asList(1, 2, 3);
        Iterable<Integer> secondInputIterable = asList(4, 5, 6);

        // When
        Collection<Integer> actualCollection = collectionOf(Integer.class)
                .with(firstInputIterable)
                .with(secondInputIterable)
                .build();

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldReturnACollectionContainingAllElementsInTheSuppliedIterable() throws Exception {
        // Given
        Collection<Integer> expectedCollection = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> firstInputIterable = asList(1, 2, 3);
        Iterable<Integer> secondInputIterable = asList(4, 5, 6);

        // When
        Collection<Integer> actualCollection = collectionFrom(firstInputIterable).with(secondInputIterable);

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldReturnACollectionContainingAllElementsInTheSuppliedArray() throws Exception {
        // Given
        String[] elementArray = new String[]{"a", "b", "c"};
        Collection<String> expectedCollection = asList("a", "b", "c");

        // When
        Collection<String> actualCollection = collectionFrom(elementArray);

        // Then
        assertThat(actualCollection, is(expectedCollection));
    }

    @Test
    public void shouldReturnAIterableContainingTheSuppliedElements() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(5, 10, 15);

        // When
        Iterable<Integer> actualList = iterableWith(5, 10, 15);

        // Then
        assertThat(actualList, is(expectedIterable));
    }

    @Test
    public void shouldAllowMoreElementsToBeAddedToTheIterableWithAnd() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(5, 10, 15, 20, 25, 30);

        // When
        Iterable<Integer> actualIterable = iterableWith(5, 10, 15).and(20, 25, 30);

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheIterableWithAnd() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(5, 10, 15, 20, 25, 30);
        Integer[] elementArray = new Integer[]{20, 25, 30};

        // When
        Iterable<Integer> actualList = iterableWith(5, 10, 15).and(elementArray);

        // Then
        assertThat(actualList, is(expectedIterable));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheIterableWithAnd() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> someOtherElements = asList(4, 5, 6);

        // When
        Iterable<Integer> actualIterable = iterableWith(1, 2, 3).and(someOtherElements);

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldAllowElementsToBeAddedToAIterableWithWith() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(1, 2, 3, 4, 5, 6);

        // When
        Iterable<Integer> actualIterable = iterableOf(Integer.class)
                .with(1, 2, 3)
                .with(4, 5, 6)
                .build();

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheIterableWithWith() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(5, 10, 15, 20, 25, 30);
        Integer[] elementArray = new Integer[]{20, 25, 30};

        // When
        Iterable<Integer> actualIterable = iterableWith(5, 10, 15).with(elementArray);

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheIterableWithWith() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> firstInputIterable = asList(1, 2, 3);
        Iterable<Integer> secondInputIterable = asList(4, 5, 6);

        // When
        Iterable<Integer> actualIterable = iterableOf(Integer.class)
                .with(firstInputIterable)
                .with(secondInputIterable)
                .build();

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldReturnAIterableContainingAllElementsInTheSuppliedIterable() throws Exception {
        // Given
        Iterable<Integer> expectedIterable = asList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> firstInputIterable = asList(1, 2, 3);
        Iterable<Integer> secondInputIterable = asList(4, 5, 6);

        // When
        Iterable<Integer> actualIterable = iterableFrom(firstInputIterable).with(secondInputIterable);

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldReturnAIterableContainingAllElementsInTheSuppliedArray() throws Exception {
        // Given
        String[] elementArray = new String[]{"a", "b", "c"};
        Iterable<String> expectedIterable = asList("a", "b", "c");

        // When
        Iterable<String> actualIterable = iterableFrom(elementArray);

        // Then
        assertThat(actualIterable, is(expectedIterable));
    }

    @Test
    public void shouldReturnAnEmptyList() throws Exception {
        // Given
        List<Integer> expected = new ArrayList<Integer>();

        // When
        List<Integer> actual = list();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAnEmptyListWithElementsOfTheSpecifiedType() throws Exception {
        // Given
        List<Integer> expected = new ArrayList<Integer>();

        // Then
        assertThat(listOf(Integer.class), is(expected));
    }

    @Test
    public void shouldReturnAListContainingTheSuppliedElements() {
        // Given
        List<Integer> expectedList = asList(5, 10, 15);

        // When
        List<Integer> actualList = listWith(5, 10, 15);

        // Then
        assertThat(actualList, is(expectedList));
    }

    @Test
    public void shouldReturnAListContainingAllElementsInTheSuppliedIterable() {
        // Given
        List<Integer> expected = asList(5, 10, 15);
        Iterable<Integer> elements = asList(5, 10, 15);

        // When
        List<Integer> actual = listFrom(elements);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAListContainingAllElementsInTheSuppliedArray() {
        // Given
        List<Integer> expected = asList(5, 10, 15);
        Integer[] elements = new Integer[]{5, 10, 15};

        // When
        List<Integer> actual = listFrom(elements);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAnEmptyListBuilder() throws Exception {
        // Given
        ListBuilder<Integer> expected = new ListBuilder<Integer>();

        // When
        ListBuilder<Integer> actual = listBuilder();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAnEmptyListBuilderWithElementsOfTheSpecifiedType() throws Exception {
        // Given
        ListBuilder<Integer> expected = new ListBuilder<Integer>();

        // Then
        assertThat(listBuilderOf(Integer.class), is(expected));
    }

    @Test
    public void shouldReturnAListBuilderWithTheSuppliedElements() {
        // Given
        ListBuilder<Integer> expected = new ListBuilder<Integer>().with(5, 10, 15);

        // When
        ListBuilder<Integer> actual = listBuilderWith(5, 10, 15);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAListBuilderContainingAllElementsInTheSuppliedIterable() {
        // Given
        ListBuilder<Integer> expected = new ListBuilder<Integer>().with(5, 10, 15);
        Iterable<Integer> elements = asList(5, 10, 15);

        // When
        ListBuilder<Integer> actual = listBuilderFrom(elements);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAListBuilderContainingAllElementsInTheSuppliedArray() {
        // Given
        ListBuilder<Integer> expected = new ListBuilder<Integer>().with(5, 10, 15);
        Integer[] elements = new Integer[]{5, 10, 15};

        // When
        ListBuilder<Integer> actual = listBuilderFrom(elements);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnAMultisetContainingTheSuppliedElements() {
        // Given
        Multiset<String> expectedMultiset = HashMultiset.create(iterableWith("a", "a", "b", "c"));

        // When
        Multiset<String> actualMultiset = multisetWith("a", "a", "b", "c");

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowMoreElementsToBeAddedToTheMultisetWithAnd() {
        // Given
        Multiset<String> expectedMultiset = HashMultiset.create(iterableWith("a", "a", "b", "c", "c", "d"));

        // When
        Multiset<String> actualMultiset = multisetWith("a", "a", "b").and("c", "c", "d");

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheMultisetWithAnd() throws Exception {
        // Given
        Multiset<Integer> expectedMultiset = HashMultiset.create(asList(1, 1, 2, 3, 3, 4));
        Integer[] elementArray = new Integer[]{1, 3, 4};

        // When
        Multiset<Integer> actualMultiset = multisetWith(1, 2, 3).and(elementArray);

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheMultisetWithAnd() throws Exception {
        // Given
        Multiset<String> expectedMultiset = HashMultiset.create(iterableWith("a", "a", "b", "c"));
        Iterable<String> someOtherElements = iterableWith("b", "c");

        // When
        Multiset<String> actualMultiset = multisetWith("a", "a").and(someOtherElements);

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowElementsToBeAddedToAMultisetWithWith() throws Exception {
        // Given
        Multiset<Integer> expectedMultiset = HashMultiset.create(asList(1, 1, 2, 2, 3, 3));

        // When
        Multiset<Integer> actualMultiset = multisetOf(Integer.class)
                .with(1, 1, 2)
                .with(2, 3, 3)
                .build();

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToAMultisetWithWith() throws Exception {
        Multiset<Integer> expectedMultiset = HashMultiset.create(asList(1, 1, 2, 4, 5, 6));
        Integer[] elementArray = new Integer[]{1, 4, 5};

        // When
        Multiset<Integer> actualMultiset = multisetWith(1, 2, 6).with(elementArray);

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToAMultisetWithWith() throws Exception {
        // Given
        Multiset<Integer> expectedMultiset = HashMultiset.create(asList(1, 1, 2, 4, 5, 6));
        Iterable<Integer> firstInputIterable = asList(1, 2, 4);
        Iterable<Integer> secondInputIterable = asList(1, 5, 6);

        // When
        Multiset<Integer> actualMultiset = multisetOf(Integer.class)
                .with(firstInputIterable)
                .with(secondInputIterable)
                .build();

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldReturnAMultisetContainingAllElementsInTheSuppliedIterable() throws Exception {
        // Given
        Multiset<Integer> expectedMultiset = HashMultiset.create(asList(1, 1, 2, 4, 5, 6));
        Iterable<Integer> firstInputIterable = asList(1, 2, 4);
        Iterable<Integer> secondInputIterable = asList(1, 5, 6);

        // When
        Multiset<Integer> actualMultiset = multisetFrom(firstInputIterable).with(secondInputIterable);

        // Then
        assertThat(actualMultiset, is(expectedMultiset));
    }

    @Test
    public void shouldReturnAMultisetContainingAllElementsInTheSuppliedArray() throws Exception {
        // Given
        Multiset<Integer> expectedBag = HashMultiset.create(asList(1, 1, 2));
        Integer[] elementArray = new Integer[]{1, 1, 2};

        // When
        Multiset<Integer> actualBag = multisetFrom(elementArray);

        // Then
        assertThat(actualBag, is(expectedBag));
    }

    @Test
    public void shouldReturnASetContainingTheSuppliedElements() {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b"));

        // When
        Set<String> actualSet = setWith("a", "a", "b");

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowMoreElementsToBeAddedToTheSetWithAnd() {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));

        // When
        Set<String> actualSet = setWith("a", "a", "b").and("b", "c", "c");

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheSetWithAnd() throws Exception {
// Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        String[] elementArray = new String[]{"a", "a", "b"};

        // When
        Set<String> actualSet = setWith("a", "c").and(elementArray);

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheSetWithAnd() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        Iterable<String> someOtherElements = iterableWith("b", "c", "c");

        // When
        Set<String> actualSet = setWith("a", "b").and(someOtherElements);

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowElementsToBeAddedToASetWithWith() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));

        // When
        Set<String> actualSet = setOf(String.class)
                .with("a", "a")
                .with("b", "c")
                .build();

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowArraysOfElementsToBeAddedToTheSetWithWith() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        String[] elementArray = new String[]{"a", "a", "b"};

        // When
        Set<String> actualSet = setWith("a", "c").with(elementArray);

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldAllowIterablesOfElementsToBeAddedToTheSetWithWith() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        Iterable<String> firstInputIterable = iterableWith("a", "a");
        Iterable<String> secondInputIterable = iterableWith("b", "c", "c");

        // When
        Set<String> actualSet = setOf(String.class)
                .with(firstInputIterable)
                .with(secondInputIterable)
                .build();

        // Then
        assertThat(actualSet, is(expectedSet));
    }


    @Test
    public void shouldReturnASetContainingAllElementsInTheSuppliedIterable() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        Iterable<String> firstInputIterable = iterableWith("a", "a");
        Iterable<String> secondInputIterable = iterableWith("b", "c", "c");

        // When
        Set<String> actualSet = setFrom(firstInputIterable).with(secondInputIterable);

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldReturnASetContainingAllElementsInTheSuppliedArray() throws Exception {
        // Given
        Set<String> expectedSet = new HashSet<String>(collectionWith("a", "b", "c"));
        String[] elementArray = new String[]{"a", "b", "c", "c"};

        // When
        Set<String> actualSet = setFrom(elementArray);

        // Then
        assertThat(actualSet, is(expectedSet));
    }

    @Test
    public void shouldReturnAMapContainingTheSuppliedKeyAndValue() {
        // Given
        Map<Integer, String> expectedMap = new HashMap<Integer, String>();
        expectedMap.put(5, "a");

        // When
        Map<Integer, String> actualMap = mapWith(5, "a");

        // Then
        assertThat(actualMap, is(expectedMap));
    }

    @Test
    public void shouldAllowMoreKeysAndValuesToBeAddedToTheMapWithAnd() {
        // Given
        Map<Integer, String> expectedMap = new HashMap<Integer, String>();
        expectedMap.put(5, "a");
        expectedMap.put(10, "b");
        expectedMap.put(15, "c");

        // When
        Map<Integer, String> actualMap = mapWith(5, "a").and(10, "b").and(15, "c");

        // Then
        assertThat(actualMap, is(expectedMap));
    }

    @Test
    public void shouldAllowKeysAndValuesToBeAddedToAMapWithWith() throws Exception {
        // Given
        Map<Integer, String> expectedMap = new HashMap<Integer, String>();
        expectedMap.put(5, "a");
        expectedMap.put(10, "b");
        expectedMap.put(15, "c");

        // When
        Map<Integer, String> actualMap = mapOf(Integer.class, String.class)
                .with(5, "a")
                .with(10, "b")
                .with(15, "c")
                .build();

        // Then
        assertThat(actualMap, is(expectedMap));
    }

    @Test
    public void shouldReturnASingleContainingTheSuppliedElement() {
        // Given
        Single<Integer> expectedSingle = new Single<Integer>(5);

        // When
        Single<Integer> actualSingle = tuple(5);

        // Then
        assertThat(actualSingle, is(expectedSingle));
    }

    @Test
    public void shouldReturnAPairContainingTheSuppliedElements() {
        // Given
        Pair<Integer, String> expectedPair = new Pair<Integer, String>(5, "Five");

        // When
        Pair<Integer, String> actualPair = tuple(5, "Five");

        // Then
        assertThat(actualPair, is(expectedPair));
    }

    @Test
    public void shouldReturnATripleContainingTheSuppliedElements() {
        // Given
        Triple<Integer, String, Boolean> expectedTriple =
                new Triple<Integer, String, Boolean>(5, "Five", true);

        // When
        Triple<Integer, String, Boolean> actualTriple = tuple(5, "Five", true);

        // Then
        assertThat(actualTriple, is(expectedTriple));
    }

    @Test
    public void shouldReturnAQuadrupleContainingTheSuppliedElements() {
        // Given
        Quadruple<Integer, String, Boolean, Double> expectedQuadruple =
                new Quadruple<Integer, String, Boolean, Double>(5, "Five", true, 1.6);

        // When
        Quadruple<Integer, String, Boolean, Double> actualQuadruple = tuple(5, "Five", true, 1.6);

        // Then
        assertThat(actualQuadruple, is(expectedQuadruple));
    }

    @Test
    public void shouldReturnAQuintupleContainingTheSuppliedElements() {
        // Given
        Quintuple<Integer, String, Boolean, Double, Long> expectedQuintuple =
                new Quintuple<Integer, String, Boolean, Double, Long>(5, "Five", true, 1.6, 26L);

        // When
        Quintuple<Integer, String, Boolean, Double, Long> actualQuintuple = tuple(5, "Five", true, 1.6, 26L);

        // Then
        assertThat(actualQuintuple, is(expectedQuintuple));
    }

    @Test
    public void shouldReturnASextupleContainingTheSuppliedElements() {
        // Given
        Sextuple<Integer, String, Boolean, Double, Long, Name> expectedSextuple =
                new Sextuple<Integer, String, Boolean, Double, Long, Name>(5, "Five", true, 1.6, 26L, name("fred"));

        // When
        Sextuple<Integer, String, Boolean, Double, Long, Name> actualSextuple = tuple(5, "Five", true, 1.6, 26L, name("fred"));

        // Then
        assertThat(actualSextuple, is(expectedSextuple));
    }

    @Test
    public void shouldReturnASeptupleContainingTheSuppliedElements() {
        // Given
        Septuple<Integer, String, Boolean, Double, Long, Name, Colour> expectedSeptuple =
                new Septuple<Integer, String, Boolean, Double, Long, Name, Colour>(5, "Five", true, 1.6, 26L, name("fred"), colour("red"));

        // When
        Septuple<Integer, String, Boolean, Double, Long, Name, Colour> actualSeptuple =
                tuple(5, "Five", true, 1.6, 26L, name("fred"), colour("red"));

        // Then
        assertThat(actualSeptuple, is(expectedSeptuple));
    }

    @Test
    public void shouldReturnAOctupleContainingTheSuppliedElements() {
        // Given
        Octuple<Integer, String, Boolean, Double, Long, Name, Colour, Age> expectedOctuple =
                new Octuple<Integer, String, Boolean, Double, Long, Name, Colour, Age>(
                        5, "Five", true, 1.6, 26L, name("fred"), colour("red"), age(25));

        // When
        Octuple<Integer, String, Boolean, Double, Long, Name, Colour, Age> actualOctuple =
                tuple(5, "Five", true, 1.6, 26L, name("fred"), colour("red"), age(25));

        // Then
        assertThat(actualOctuple, is(expectedOctuple));
    }

    @Test
    public void shouldReturnANonupleContainingTheSuppliedElements() {
        // Given
        Nonuple<Integer, String, Boolean, Double, Long, Name, Colour, Age, Location> expectedNonuple =
                new Nonuple<Integer, String, Boolean, Double, Long, Name, Colour, Age, Location>(
                        5, "Five", true, 1.6, 26L, name("fred"), colour("red"), age(25), location("London"));

        // When
        Nonuple<Integer, String, Boolean, Double, Long, Name, Colour, Age, Location> actualNonuple =
                tuple(5, "Five", true, 1.6, 26L, name("fred"), colour("red"), age(25), location("London"));

        // Then
        assertThat(actualNonuple, is(expectedNonuple));
    }
}
