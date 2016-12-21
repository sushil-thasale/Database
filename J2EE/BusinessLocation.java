import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

@Entity
@Access(FIELD)
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"isLocationFor"}))
public class BusinessLocation implements Serializable{
	
	public BusinessLocation() {
    }
	
    /** Primary key. */
    @Id
    private int id;

    /** description attribute. */
    @Column(name="description", length=31, nullable=true)
    private String description;

    /** locatedAt attribute. */
    @ManyToOne(optional=false, cascade=CascadeType.REFRESH)
    @JoinColumn(name="locatedAt", nullable=false)
    private Building locatedAt;
    
    /** partOf attribute. */
    @ManyToOne(optional=false, cascade=CascadeType.REFRESH)
    @JoinColumn(name="partOf", nullable=false)
    private Business partOf;
 
    /*isLocation For attribute*/
    @OneToOne(mappedBy="isLocationFor", optional=true, cascade=CascadeType.REFRESH)
    @JoinColumn(name="isLocationFor", nullable=true)
    private Business isLocationFor;
    
    @Basic
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Basic
    public Business getPartOf() {
        return partOf;
    }
    public void setPartOf(Business partOf) {
        this.partOf = partOf;
    }
    
    @Basic
    public Building getLocatedAt() {
        return locatedAt;
    }
    public void setLocatedAt(Building locatedAt) {
        this.locatedAt = locatedAt;
    }
    
    @Basic
    public Business getIsLocationFor() {
        return isLocationFor;
    }
    public void setIsLocationFor(Business isLocationFor) {
        this.isLocationFor = isLocationFor;
    }
}

