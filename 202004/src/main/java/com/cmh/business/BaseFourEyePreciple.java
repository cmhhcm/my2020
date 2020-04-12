package com.cmh.business;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author:起舞的日子
 * @date: 2020/4/10 上午7:19
 */
public abstract class BaseFourEyePreciple implements Serializable {

    public void apply(String submitter, String auditor, DateTime submittedTime, int fourEyeHours) {
        /**
         * 是否可操作
         */
         boolean operable;

        /**
         * 不可操作原因
         */
         String notOperableReason;

        if (!submitter.equals(auditor)) {
            operable = true;
            return;
        }
        if (submittedTime == null) {
            operable = true;
            return;
        }
        if (submittedTime.plusHours(fourEyeHours).isBeforeNow()) {
            operable = true;
            return;
        }
        operable = false;
        notOperableReason = String.format("待团队成员确认，请于%d小时候操作", fourEyeHours);

    }
}
