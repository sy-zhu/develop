package com.develop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点工具类
 * 
 * @author chenht
 *
 */
public class Node {
	private Integer id;
	private String nodeId; // 节点id
	private String pid; // 父节点id
	private String text; // 节点名字
	private String icon;
	private String href; // 点击节点触发链接
	private String type;
	private State state = new State(false, false, /* true, */false);
	private List<Node> nodes; // 孩子

	public Node() {
		this.nodes = new ArrayList<Node>();
	}

	public Node(String nodeId, String pId) {
		this.nodeId = nodeId;
		this.pid = pId;
		this.nodes = new ArrayList<Node>();
	}

	public Node(String nodeId, String pId, String text, String icon, String href) {
		super();
		this.nodeId = nodeId;
		this.pid = pId;
		this.text = text;
		this.icon = icon;
		this.href = href;
		this.nodes = new ArrayList<Node>();
	}

	/**
	 * 根据list类型的节点集合，构建一棵树，必须给定根节点
	 * 
	 * @param nodes
	 * @return
	 */
	public Node createTree(List<Node> nodes) {
		if (nodes == null || nodes.size() < 0)
			return null;
		Node root = new Node("root", "0");
		for (Node node : nodes) {
			if (node.getPid().equals("0") || node.getPid().equals("root")) {
				root.getNodes().add(node);
			} else {
				addChild(root, node);
			}
		}
		return root;
	}

	/**
	 * 添加子节点
	 * 
	 * @param node
	 * @param child
	 */
	public void addChild(Node node, Node child) {
		for (Node item : node.getNodes()) {
			if (item.getNodeId().equals(child.getPid())) {
				item.getNodes().add(child);
				break;
			} else {
				if (item.getNodes() != null && item.getNodes().size() > 0) {
					addChild(item, child);
				}
			}
		}
	}

	/**
	 * 遍历
	 * 
	 * @param node
	 * @return
	 */
	public String iteratorTree(Node node) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n");
		if (node != null) {
			for (Node index : node.getNodes()) {
				buffer.append(index.getNodeId() + ",");
				if (index.getNodes() != null && index.getNodes().size() > 0) {
					buffer.append(iteratorTree(index));
				}
			}
		}
		buffer.append("\n");
		return buffer.toString();
	}

	List<Node> node = new ArrayList<Node>();

	/**
	 * 中序解析树，保存到list
	 * 
	 * @param nodes
	 * @return
	 */
	public Node zxPraseTree(Node nodes) {
		if (nodes != null) {
			for (Node index : nodes.getNodes()) {
				node.add(index);
				if (index.getNodes() != null && index.getNodes().size() > 0) {
					node.add(zxPraseTree(index));
				}
			}
		}
		// buffer.append("\n");
		Node n = new Node();
		n.setNodes(node);
		return n;
	}

	public class State {
		private Boolean checked;
		private Boolean selected;
		// private Boolean expanded;
		private Boolean disabled;

		/**
		 * @param checked
		 * @param selected
		 * @param expanded
		 * @param disabled
		 */
		public State(Boolean checked, Boolean selected, /* Boolean expanded, */ Boolean disabled) {
			super();
			this.checked = checked;
			this.selected = selected;
			// this.expanded = expanded;
			this.disabled = disabled;
		}

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

		public Boolean getSelected() {
			return selected;
		}

		public void setSelected(Boolean selected) {
			this.selected = selected;
		}

		/*
		 * public Boolean getExpanded() { return expanded; } public void
		 * setExpanded(Boolean expanded) { this.expanded = expanded; }
		 */
		public Boolean getDisabled() {
			return disabled;
		}

		public void setDisabled(Boolean disabled) {
			this.disabled = disabled;
		}
	}

	public static void main(String[] args) {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("系统管理", "0"));
		nodes.add(new Node("用户管理", "系统管理"));
		nodes.add(new Node("角色管理", "系统管理"));
		nodes.add(new Node("资源管理", "系统管理"));
		nodes.add(new Node("添加用户", "用户管理"));
		nodes.add(new Node("删除用户", "用户管理"));
		nodes.add(new Node("修改角色", "角色管理"));

		Node tree = new Node();
		Node mt = tree.createTree(nodes);
		System.out.println(tree.iteratorTree(mt));
		System.out.println("===========");
		Node n = tree.zxPraseTree(mt);
		List<Node> list = n.getNodes();
		for (Node node : list) {
			System.out.println(node.getNodeId());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
