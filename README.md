# Design and Implementation of Coal Sales System Based on SpringBoot+Thymeleaf

Technology involved: Spring Boot, MyBatis - Plus, Layui, Thymeleaf, jQuery, MySQL

## The system is designed to complete the following functions:
## function:
1.Customer information module:
This module is mainly to manage the basic information of customers.
- Operator can add customer information,
- You can also view customer details
- and modify and delete customer information, etc.
2. Contract management block:
This module is mainly about the management of the detailed information of the contract signed between the enterprise and the customer.
- Operator can add signed contract information,
- You can also view contract details
- and the corresponding information to modify and delete.
Weighing management module:
This module is mainly to collect the information of vehicles to manage the actual coal sales
- Operator can collect the information when the vehicle enters the plant
- and factory information.
4. Statistical management block:
The module can be carried out on the sale of coal
- Daily sales volume,
- Monthly sales statistics,
- Annual sales statistics,
- Annual planned production statistics.
5. System management module:
This module mainly manages the users and roles of the system.
- You can assign roles to system users,
- You can assign permissions to roles.

# 基于SpringBoot+Thymeleaf的煤炭销售系统的设计与实现

涉及到的技术: Spring Boot、Mybatis-plus、layui、thymeleaf、jquery、mysql

描述：信息化建设是企业提高管理水平,迈向现代化管理的重要标志。近年来，各行业都在深入地探讨如何加强企业管理，研发适应本企业特点的管理软件系统。管理信息系的是一门集管理科学、信息科学、系统科学及计算机科学为一体的综合性学科，研究的是企业中信息管理活动的全过程，以便有效的管理信息，提供各类管理决策信息,辅助企业进行现代化管理。对于煤炭销售企业，由于其生产条件的复杂性和多变性，生产模式和管理方式的特殊性，在进行企业信息化建设上，无法直接借鉴其他行业管理信息系统的模式。煤炭销售企业应结合煤炭销售行业特征和本企业管理实际，研究和开发适应自身实际情况的管理信息系统，从而全面提高企业的经营管理水平。它将mysql作为该系统开发的数据库技术,Idea作为开发平台,用UML统一建模语言进行开发设计,保障了煤炭销售相关数据的准确度,做到实时更新,动态了解其发展情况。

## 现设计系统完成以下功能：

## 功能:
### 1.账号管理模块：
 -该模块主要是对煤炭公司的工作人员的基本信息进行管理，拥有该管理模块功能的操作员可以对工作人员进行相应的操作，可以添加工作人员，给工作人员分配角色，对工作人员的信息进行修改和删除等。
### 2.客户信息管理模块：
 -该模块主要是对客户的基本信息进行管理，操作员可以添加客户信息，还可以查看客户详细信息以及对客户信息进行修改和删除等。
### 3.合同管理模块：
 -该模块主要是对企业与客户签订合同的详细信息的管理，操作员可以添加签约完成的合同信息，可以查看合同详细信息以及对相应信息进行修改和删除，还可以根据合同的效力状态选择出厂等。
### 4.产品管理模块：
 -该模块主要是对煤炭各种类的信息进行管理，操作员可以进厂添加产品数量，还可以对产品信息进行修改和删除等。
### 5.销售管理模块：
 -该模块主要是对煤炭产生的出厂信息进行查看和删除，通过产生的出厂单进而统计煤炭的销售量，有煤炭种类总销售量、总销售额、利润、纳税以及月销售和年销售量的统计。
### 6.系统管理模块：
 -该模块主要是对系统的用户、角色进行管理，可以给系统用户进行分配角色，可以给角色分配权限。
