<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="account-detail">
    <h1>My Account</h1>
    <div class="account-detail-main">
        <div class="account-detail-left">
            <div class="content-detail-left">
                <h3>User information</h3>
                <p>You can change your information in here</p>
            </div>
            <div class="call-me-1">
                <div class="full-name">
                    <h3>Full name</h3>
                    <div class="full-name-div">
                        <div id="bd-input">
                            <input class="full-name-div-input" type="text" required>
                            <label for="first_name" class="this-label">First name</label>
                        </div>
                        <div>
                            <input  class="full-name-div-input" type="text" required>
                            <label for="last_name" class="this-label">Last name</label>
                        </div>
                    </div>
                </div>
                <div class="address">
                    <h3>Email</h3>
                    <div class="address-div">
                        <input class="address-div-input" type="text" required>
                        <label for="email" class="address-label-div">Email</label>
                    </div>
                </div>
                <div class="address">
                    <h3>Phone</h3>
                    <div class="address-div">
                        <input class="address-div-input" type="text" required>
                        <label for="number" class="address-label-div">Phone</label>
                    </div>
                </div>
                <div class="info-change-button">
                    <button id="button-super-1">Cancel</button>
                    <button id="button-super-2">Save</button>
                </div>
            </div>

            <div class="content-detail-left oke-let-me">
                <h3>Change password</h3>
                <p>You can change your password in here</p>
            </div>
            <div class="call-me-1 call-me-hidden">
                <div class="address">
                    <h3>Password</h3>
                    <div class="address-div">
                        <input class="address-div-input" type="text" required>
                        <label for="email" class="address-label-div">Password</label>
                    </div>
                </div>
                <div class="address">
                    <h3>Change password</h3>
                    <div class="address-div">
                        <input class="address-div-input" type="text" required>
                        <label for="email" class="address-label-div">Retype password</label>
                    </div>
                </div>
                <div class="info-change-button">
                    <button id="button-super-1">Cancel</button>
                    <button id="button-super-2">Save</button>
                </div>
            </div>
        </div>


        <div class="account-detail-right">
            <h3>Profile photo</h3>
            <div>
                <img src="${currentUser.avatar}" id="user-info-avatar" alt="avatar">
            </div>
            <button>Choose you photo</button>

            <div class="change-password">
                <h3 id="change-pass-h3">Change password</h3>
                <div class="change-password-form">
                    <input type="text" class="change-password-form-input" required>
                    <label for="password" class="change-password-form-label">Change password</label>
                </div>
                <div class="change-password-form">
                    <input type="text" class="change-password-form-input" required>
                    <label for="cpass"  class="change-password-form-label">Retype password</label>
                </div>
                <div class="info-change-button">
                    <button id="button-super-1">Cancel</button>
                    <button id="button-super-2">Save</button>
                </div>
            </div>
        </div>
    </div>
</div>
