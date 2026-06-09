const { PrismaClient } = require("@prisma/client");
const prisma = new PrismaClient();
async function main() {
  let adminRole = await prisma.role.findUnique({ where: { name: "ADMIN" } });
  if (!adminRole) {
    adminRole = await prisma.role.create({ data: { name: "ADMIN" } });
    console.log("Created ADMIN role");
  } else {
    console.log("ADMIN role already exists");
  }
  const email = "cuonghoang1103@gmail.com";
  const existing = await prisma.user.findUnique({ where: { email } });
  if (existing) {
    await prisma.user.update({ where: { email }, data: { password: "Cuong123@", username: "Cuong03dx" } });
    const ur = await prisma.userRole.findUnique({ where: { userId_roleId: { userId: existing.id, roleId: adminRole.id } } });
    if (!ur) await prisma.userRole.create({ data: { userId: existing.id, roleId: adminRole.id } });
    console.log("Updated admin: Cuong03dx / Cuong123@");
  } else {
    const user = await prisma.user.create({
      data: {
        username: "Cuong03dx",
        email,
        password: "Cuong123@",
        fullName: "Admin",
        enabled: true,
        accountNonExpired: true,
        accountNonLocked: true,
        credentialsNonExpired: true,
        provider: "local",
        roles: { create: { roleId: adminRole.id } }
      }
    });
    console.log("Created admin: Cuong03dx / Cuong123@ (ID: " + user.id + ")");
  }
}
main().catch(e => { console.error(e.message); process.exit(1); }).finally(() => prisma.$disconnect());
