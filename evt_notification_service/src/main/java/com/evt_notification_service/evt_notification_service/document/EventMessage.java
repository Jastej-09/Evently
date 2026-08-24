package com.evt_notification_service.evt_notification_service.document;

import java.time.Instant;
import java.util.UUID;
public record EventMessage( UUID eventId,
        String eventype,Instant occurentAt, EventPayload payload)
{


}
