package com.baconfire.authserver.domain.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceStatus{
    private String statusCode;
    private boolean success;
    private String errorMessage;
}
