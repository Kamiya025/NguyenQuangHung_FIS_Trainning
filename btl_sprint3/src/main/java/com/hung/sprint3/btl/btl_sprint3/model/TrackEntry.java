package com.hung.sprint3.btl.btl_sprint3.model;


import com.hung.sprint3.btl.btl_sprint3.model.util.TrackAction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class TrackEntry extends AbstractEntity {

    private LocalDateTime date;
    private Evidence evidence;
    private Detective detective;
    private TrackAction trackAction;
    private String reason;

    public TrackEntry(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt, LocalDateTime date, Evidence evidence, Detective detective, TrackAction trackAction, String reason) {
        super(id, version, createAt, modifiedAt);
        this.date = date;
        this.evidence = evidence;
        this.detective = detective;
        this.trackAction = trackAction;
        this.reason = reason;
    }

}
