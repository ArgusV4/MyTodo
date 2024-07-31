package com.argus.mytodo.jwt;

import com.argus.mytodo.config.TimeZoneInitializer;
import lombok.Data;
import java.util.Date;

@Data
public class AuthResponse {
    private String token;
    private String expiredAt;

    public AuthResponse(String token, Date expiredAt) {
        this.token = token;
        this.expiredAt =  TimeZoneInitializer.formatDateToTunisFrench(expiredAt);
    }
}
