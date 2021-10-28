package xxx.xxx;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DataClass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String elementInfo;
}
