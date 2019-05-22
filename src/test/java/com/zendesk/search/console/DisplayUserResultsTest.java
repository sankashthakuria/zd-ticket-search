package com.zendesk.search.console;

import com.zendesk.search.model.results.ZdSearchResults;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/** Created by @author Sankash on 5/20/2019 */
class DisplayUserResultsTest {

  @Test
  void test_show_method_called_once_successfully() {
    ZdSearchResults zdSearchResults = mock(ZdSearchResults.class);
    DisplayUserResults display = mock(DisplayUserResults.class);
    doNothing().when(display).show(isA(ZdSearchResults.class));
    display.show(zdSearchResults);
    verify(display, times(1)).show(zdSearchResults);
  }

  @Test
  void test_show_real_method_called_successfully() {
    ZdSearchResults zdSearchResults = mock(ZdSearchResults.class);
    DisplayUserResults display = mock(DisplayUserResults.class);
    doCallRealMethod().when(display).show(any(ZdSearchResults.class));
    display.show(zdSearchResults);
    verify(display, times(1)).show(zdSearchResults);
  }
}
