package demo.entity;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
    private static final long serialVersionUID = -7282513091440103475L;

    private String name;
    private Integer age;
    private List<Address> addressList;
}
