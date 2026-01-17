package com.tbde.indicator;

import com.tbde.preprocess.ProcessedText;

public interface IndicatorDetector {

    IndicatorResult detect(ProcessedText text);

    IndicatorType getType();
}
