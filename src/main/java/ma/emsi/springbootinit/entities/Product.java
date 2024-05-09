package ma.emsi.springbootinit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//@Data = @Setter + @Getter + ...
@Data @NoArgsConstructor @AllArgsConstructor
@Entity //Pour la création de cette classe en table
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String ref;
    private Integer code;
    @Temporal(TemporalType.DATE)
    private Date prodDate;
    private Float price;
}
