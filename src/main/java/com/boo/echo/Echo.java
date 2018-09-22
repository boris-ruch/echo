package com.boo.echo;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Echo {

        private final long requestCounter;

        private final long passedValue;

        private final LocalDateTime localDateTime;

}
