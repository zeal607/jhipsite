import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import sysUser, {
  SysUserState
} from 'app/entities/sys-user/sys-user.reducer';
// prettier-ignore
import sysRole, {
  SysRoleState
} from 'app/entities/sys-role/sys-role.reducer';
// prettier-ignore
import sysMenu, {
  SysMenuState
} from 'app/entities/sys-menu/sys-menu.reducer';
// prettier-ignore
import sysUserRole, {
  SysUserRoleState
} from 'app/entities/sys-user-role/sys-user-role.reducer';
// prettier-ignore
import sysRoleMenu, {
  SysRoleMenuState
} from 'app/entities/sys-role-menu/sys-role-menu.reducer';
// prettier-ignore
import sysCompany, {
  SysCompanyState
} from 'app/entities/sys-company/sys-company.reducer';
// prettier-ignore
import sysOffice, {
  SysOfficeState
} from 'app/entities/sys-office/sys-office.reducer';
// prettier-ignore
import sysEmployee, {
  SysEmployeeState
} from 'app/entities/sys-employee/sys-employee.reducer';
// prettier-ignore
import sysEmployeeOffice, {
  SysEmployeeOfficeState
} from 'app/entities/sys-employee-office/sys-employee-office.reducer';
// prettier-ignore
import sysCompanyOffice, {
  SysCompanyOfficeState
} from 'app/entities/sys-company-office/sys-company-office.reducer';
// prettier-ignore
import sysPost, {
  SysPostState
} from 'app/entities/sys-post/sys-post.reducer';
// prettier-ignore
import sysEmployeePost, {
  SysEmployeePostState
} from 'app/entities/sys-employee-post/sys-employee-post.reducer';
// prettier-ignore
import sysRoleDataScope, {
  SysRoleDataScopeState
} from 'app/entities/sys-role-data-scope/sys-role-data-scope.reducer';
// prettier-ignore
import sysUserDataScope, {
  SysUserDataScopeState
} from 'app/entities/sys-user-data-scope/sys-user-data-scope.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sysUser: SysUserState;
  readonly sysRole: SysRoleState;
  readonly sysMenu: SysMenuState;
  readonly sysUserRole: SysUserRoleState;
  readonly sysRoleMenu: SysRoleMenuState;
  readonly sysCompany: SysCompanyState;
  readonly sysOffice: SysOfficeState;
  readonly sysEmployee: SysEmployeeState;
  readonly sysEmployeeOffice: SysEmployeeOfficeState;
  readonly sysCompanyOffice: SysCompanyOfficeState;
  readonly sysPost: SysPostState;
  readonly sysEmployeePost: SysEmployeePostState;
  readonly sysRoleDataScope: SysRoleDataScopeState;
  readonly sysUserDataScope: SysUserDataScopeState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sysUser,
  sysRole,
  sysMenu,
  sysUserRole,
  sysRoleMenu,
  sysCompany,
  sysOffice,
  sysEmployee,
  sysEmployeeOffice,
  sysCompanyOffice,
  sysPost,
  sysEmployeePost,
  sysRoleDataScope,
  sysUserDataScope,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
