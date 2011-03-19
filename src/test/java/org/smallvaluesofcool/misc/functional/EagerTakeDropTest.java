package org.smallvaluesofcool.misc.functional;

import org.junit.Test;
import org.smallvaluesofcool.misc.datastructures.TwoTuple;
import org.smallvaluesofcool.misc.functional.functors.DoFunction;
import org.smallvaluesofcool.misc.functional.functors.PredicateFunction;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.smallvaluesofcool.misc.Literals.listWith;
import static org.smallvaluesofcool.misc.Literals.twoTuple;

public class EagerTakeDropTest {
    @Test
    public void shouldTakeTheSpecifiedNumberOfElements() {
        // Given
        Iterable<String> input = listWith("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        Collection<String> expectedOutput = listWith("a", "b", "c", "d", "e");

        // When
        Collection<String> actualOutput = Eager.take(input, 5);

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldDropTheSpecifiedNumberOfElements() {
        // Given
        Iterable<String> input = listWith("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        Collection<String> expectedOutput = listWith("f", "g", "h", "i", "j");

        // When
        Collection<String> actualOutput = Eager.drop(input, 5);

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldTakeElementsWhileTheSuppliedPredicateIsTrue() throws Exception {
        // Given
        Iterable<Integer> input = listWith(1, 2, 1, 2, 3);
        Collection<Integer> expectedOutput = listWith(1, 2, 1, 2);

        // When
        Collection<Integer> actualOutput = Eager.takeWhile(input, new PredicateFunction<Integer>(){
            public boolean matches(Integer item) {
                return item < 3;
            }
        });

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldTakeElementsUntilTheSuppliedPredicateBecomesTrue() throws Exception {
        // Given
        Iterable<Integer> input = listWith(1, 2, 3, 4, 5);
        Collection<Integer> expectedOutput = listWith(1, 2, 3);

        // When
        Collection<Integer> actualOutput = Eager.takeUntil(input, new PredicateFunction<Integer>(){
            public boolean matches(Integer item) {
                return item > 3;
            }
        });

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldDropElementsWhileTheSuppliedPredicateIsTrue() throws Exception {
        // Given
        Iterable<String> input = listWith("a", "aa", "aaa", "aaaa");
        Collection<String> expectedOutput = listWith("aaa", "aaaa");

        // When
        Collection<String> actualOutput = Eager.dropWhile(input, new PredicateFunction<String>(){
            @Override
            public boolean matches(String item) {
                return item.length() < 3;
            }
        });

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldDropElementsUntilTheSuppliedPredicateBecomesTrue() throws Exception {
        // Given
        Iterable<String> input = listWith("a", "aa", "aab", "aba", "aba");
        Collection<String> expectedOutput = listWith("aab", "aba", "aba");

        // When
        Collection<String> actualOutput = Eager.dropUntil(input, new PredicateFunction<String>() {
            @Override
            public boolean matches(String item) {
                return item.contains("b");
            }
        });

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }
}