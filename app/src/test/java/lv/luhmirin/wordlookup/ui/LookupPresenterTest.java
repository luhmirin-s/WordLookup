package lv.luhmirin.wordlookup.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lv.luhmirin.wordlookup.wrapper.LookupReadyListener;
import lv.luhmirin.wordlookup.wrapper.LookupWrapper;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LookupPresenterTest {

    @Mock LookupContract mockContract;
    @Mock LookupWrapper mockLookupWrapper;
    @Captor ArgumentCaptor<LookupReadyListener> captorReadyListener;

    private LookupPresenter subject;

    private final List<String> fakeResults = Arrays.asList("test1", "test2", "test3");

    @Before
    public void setUp() {
        initMocks(this);

        subject = new LookupPresenter(mockContract, mockLookupWrapper);
    }

    @Test
    public void initializesUi_whenCreated() {
        subject.onCreate();

        verify(mockContract).prepareResultListView();
        verify(mockContract).disableInput();
        verify(mockContract).prepareInputListener();
    }

    @Test
    public void enablesInput_whenLookupReady() throws Exception {
        subject.onCreate();

        verify(mockContract).disableInput();
        verify(mockLookupWrapper).setLookupReadyListener(captorReadyListener.capture());

        captorReadyListener.getValue().onReady();

        verify(mockContract).enableInput();
    }

    @Test
    public void resetList_whenNoResults() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("1234");

        verify(mockContract).setResultList(eq(Collections.<String>emptyList()));
    }

    @Test
    public void setupList_whenThereAreResults() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).setResultList(eq(fakeResults));
    }

    @Test
    public void resetHighlightSize_whenEmptyInput() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("");

        verify(mockContract).setHighlightLength(0);
    }

    @Test
    public void setCorrectHighlightSize_whenInputNotEmpty() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).setHighlightLength(4);
    }

    @Test
    public void setPlaceholder_whenNoResults() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(Collections.<String>emptyList());

        subject.inputTextChanged("1234");

        verify(mockContract).showPlaceHolder();
    }

    @Test
    public void hidePlaceholder_whenThereAreResults() {
        when(mockLookupWrapper.lookup(anyString())).thenReturn(fakeResults);

        subject.inputTextChanged("1234");

        verify(mockContract).hidePlaceHolder();
    }
}