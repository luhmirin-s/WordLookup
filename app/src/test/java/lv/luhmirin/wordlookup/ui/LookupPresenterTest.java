package lv.luhmirin.wordlookup.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lv.luhmirin.wordlookup.LookupWrapper;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LookupPresenterTest {

    @Mock LookupContract mockContract;
    @Mock LookupWrapper mockLookupWrapper;

    private LookupPresenter subject;

    private List<String> fakeResults = Arrays.asList("aaaa", "bbbbb", "ccccc");

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        subject = new LookupPresenter(mockContract, mockLookupWrapper);
    }

    @Test
    public void initializesUi_whenCreated() throws Exception {
        subject.onCreate();

        verify(mockContract).prepareResultListView();
        verify(mockContract).prepareInputListener();
    }

    @Test
    public void resetList_whenNoResults() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("1234");

        verify(mockContract).setResultList(eq(Collections.<String>emptyList()));
    }

    @Test
    public void setupList_whenThereAreResults() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).setResultList(eq(fakeResults));
    }

    @Test
    public void resetHighlightSize_whenEmptyInput() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("");

        verify(mockContract).setHighlightLength(0);
    }

    @Test
    public void setCorrectHighlightSize_whenInputNotEmpty() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).setHighlightLength(4);
    }

    @Test
    public void setPlaceholder_whenNoResults() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("1234");

        verify(mockContract).showPlaceHolder();
    }

    @Test
    public void hidePlaceholder_whenThereAreResults() throws Exception {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).hidePlaceHolder();
    }
}