package cn.newgxu.bbs.domain.group;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.newgxu.bbs.domain.group.impl.AreaWebmasterPermission;
import cn.newgxu.bbs.domain.user.User;
import cn.newgxu.jpamodel.ObjectNotFoundException;

/**
 * 
 * @author polly
 * @since 4.0.0
 * @version $Revision 1.1$
 */
@Entity
@Table(name = "area_webmaster_group")
public class AreaWebmasterGroup extends UserGroup {

	private static final Log log = LogFactory.getLog(AreaWebmasterGroup.class);

	private static final long serialVersionUID = 3423395023992151985L;

	public AreaWebmasterGroup() {
		permission = new AreaWebmasterPermission();
	}

	@Id
	@Column(name = "id")
	// @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_seq")
	// @SequenceGenerator(name="id_seq",
	// sequenceName="seq_area_webmaster_group")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id = -1;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "max_power")
	private int maxPower;

	private int agio;

	@Column(name = "display_color")
	private String displayColor;

	@Override
	public int getAgio() {
		return agio;
	}

	@Override
	public String getDisplayColor() {
		return displayColor;
	}

	@Override
	public UserGroup getGroupByExp(int exp) {
		return null;
	}

	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public int getMaxPower() {
		return maxPower;
	}

	@Override
	public UserGroup getNextGroup() {
		return null;
	}

	@Override
	public void leavelUp(User user) {
		return;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAgio(int agio) {
		this.agio = agio;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	// ------------------------------------------------
	public static AreaWebmasterGroup get(int groupId)
			throws ObjectNotFoundException {
		return (AreaWebmasterGroup) getById(AreaWebmasterGroup.class, groupId);
	}

	public static AreaWebmasterGroup getCertainExist(int groupId) {
		try {
			return get(groupId);
		} catch (ObjectNotFoundException e) {
			log.error(e);
			log.error("严重错误：areaWebmasterGroup 没有找到。 id=" + groupId);
			throw new RuntimeException();
		}
	}

	@Override
	public int getTypeId() {
		return GroupManager.AREA_WEBMASTER_GROUP;
	}

	@Override
	public int getLeavelUpExp() {
		return 10000000;
	}

	@SuppressWarnings("unchecked")
	public static List<UserGroup> getGroups() {
		return (List<UserGroup>) Q("from AreaWebmasterGroup order by id asc")
				.getResultList();
	}

}
