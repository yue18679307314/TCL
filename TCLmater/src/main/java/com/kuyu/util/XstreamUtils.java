package com.kuyu.util;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XstreamUtils {

	// 使用注解将XML转为对象
	public static <T> T formXML(String xml, String alias, Class<?> root, Locale locale) {
		if (StringUtil.isEmpty(xml) || null == root) {
			throw new IllegalArgumentException("Fail to transform xml to object ,The xml or object'type is null");
		}
		XStream xstream = new XStream();
		if (StringUtil.isEmpty(alias)) {
			alias = "xml";
		}
		xstream.autodetectAnnotations(true);
		xstream.alias(alias, root);// 设置根节点别名
		if (locale == null) {
			locale = Locale.CHINA;
		}
		return (T) xstream.fromXML(xml);
	}

	public static <T> T fromXML(String xml, Class<?> root, String alias, Locale locale) {
		XstreamAliasMap aliasClass = new XstreamAliasMap(null, root, alias, null);
		XstreamAliasMap[] aliasClassMap = new XstreamAliasMap[1];
		aliasClassMap[0] = aliasClass;
		return (T)fromXML(xml, aliasClassMap, null, null);
	}

	// XML-->Java
	public static <T> T fromXML(String xml, XstreamAliasMap[] xstreamAlias, Locale locale, Converter[] converters) {
		if (StringUtil.isEmpty(xml)) {
			throw new IllegalArgumentException("Fail to transform xml to object ,The xml  is null");
		}
		XStream xstream = new XStream();
		setXstreamAlias(xstreamAlias, xstream);// 设置别名
		setXstreamConverter(converters, locale, xstream);// 设置类型转换器
		return (T) xstream.fromXML(xml);
	}

	public static <T> String toXML(T object, String alias, Locale locale) {
		if (object == null) {
			throw new IllegalArgumentException("Fail to transform object to xml ,The object  is null");
		}
		XstreamAliasMap aliasClass = new XstreamAliasMap(null, object.getClass(), alias, null);
		XstreamAliasMap[] aliasClassMap = new XstreamAliasMap[1];
		aliasClassMap[0] = aliasClass;
		return toXML(object, aliasClassMap, null, null, null);
	}

	// Java -->XML
	public static <T> String toXML(T object, XstreamAliasMap[] xstreamAlias, Locale locale, Converter[] converters, Map<String, Class<?>> implicitsMap) {
		if (object == null) {
			throw new IllegalArgumentException("Fail to transform object to xml ,The object  is null");
		}
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));// 解决双划线问题
		setXstreamAlias(xstreamAlias, xstream);// 设置别名
		setXstreamConverter(converters, locale, xstream);// 设置类型转换器
		// 设置隐含集合
		if (implicitsMap != null) {
			Set<Map.Entry<String, Class<?>>> implicitsSet = implicitsMap.entrySet();
			Iterator<Map.Entry<String, Class<?>>> implicitsIt = implicitsSet.iterator();
			Map.Entry<String, Class<?>> implicitsEntry = null;
			while (implicitsIt.hasNext()) {
				implicitsEntry = implicitsIt.next();
				if (implicitsEntry != null) {
					xstream.addImplicitCollection(implicitsEntry.getValue(), implicitsEntry.getKey());
				}
			}
		}
		return xstream.toXML(object);
	}

	// 设置xstream别名
	private static void setXstreamAlias(XstreamAliasMap[] xstreamAlias, XStream xstream) {
		if (xstreamAlias != null) {
			for (XstreamAliasMap alias : xstreamAlias) {
				if (alias != null) {
					if (ALIAS_CLASS_TYPE.equals(alias.type)) {
						xstream.alias(alias.alias, alias.clazz);// 设置类型别名
					} else if (ALIAS_FILED_TYPE.equals(alias.type)) {
						xstream.aliasField(alias.alias, alias.clazz, alias.filed);// 设置类型变量别名
					} else if (ALIAS_ATTR_TYPE.equals(alias.type)) {
						xstream.useAttributeFor(alias.clazz, alias.filed);
						xstream.aliasAttribute(alias.clazz, alias.filed, alias.alias); // 设置属性别名
					}
				}
			}
		}
	}

	// 设置xstream类型转换
	private static void setXstreamConverter(Converter[] converters, Locale locale, XStream xstream) {
		if (converters != null) {
			for (Converter xstreamConvert : converters) {
				if (null != xstreamConvert) {
					xstream.registerConverter(xstreamConvert);
				}
			}
		}
		if (locale == null) {
			locale = Locale.CHINA;
		}
	}

	public static Object fromXML(String alias, Class clazz, String xml) {
		XStream xstream = new XStream(new DomDriver()) {
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// 设置了这个别名才能识别下面 xml 中的 product 节点，否则要用类全限名称
		xstream.alias(alias, clazz);
		return xstream.fromXML(xml);

	}

	// 将java对象转为XML
	public static <T> String toXML(T t) {
		XStream xStream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
		xStream.autodetectAnnotations(true);
		return xStream.toXML(t);
	}

	// 將XML轉為java對象
	public static <T> T fromXML(String xmlStr, Class<?> t) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(t);
		return (T) xstream.fromXML(xmlStr);
	}

	// 别名映射关系
	static class XstreamAliasMap {
		private String filed; // 属性
		private Class<?> clazz;// 类型
		private String alias;// 属性别名
		private String type; // 别名类型

		public XstreamAliasMap(String filed, Class clazz, String alias, String type) {
			this.filed = filed;
			this.clazz = clazz;
			this.alias = alias;
			this.type = type;
		}
	}

	public static final String ALIAS_CLASS_TYPE = "0";// 类型别名
	public static final String ALIAS_FILED_TYPE = "1";// 类型成员变量别名
	public static final String ALIAS_ATTR_TYPE = "2";// 属性别名
}
