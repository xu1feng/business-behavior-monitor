package edu.xyf.monitor.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/7 19:36
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 4339234591217551919L;

    private String code;
    private String info;
    private T data;

}
